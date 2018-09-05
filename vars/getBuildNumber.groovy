import groovy.json.JsonSlurperClassic
import groovy.json.JsonOutput

def call(componentName, lvVersion) {

   def buildNumber = 0

   // The most recent build number, if one exists, is stored in 'configuration_201x.json'
   // This file is pulled from the commonbuild-configuration repository which is cloned to the workspace.
   def configurationJsonFileName = "configuration_${lvVersion}.json"
   def configurationJsonFile = readJSON file: configurationJsonFileName
   def configurationMap = new JsonSlurperClassic().parseText(configurationJsonFile.toString())

   // Check if this component has an entry in the configuration file. If so, increment the build number.
   def containsKey = configurationMap.repositories.containsKey(componentName)
   if(containsKey) {
     def componentConfiguration = configurationMap.repositories[componentName]
     if(componentConfiguration.containsKey('build_number')) {
       buildNumber = 1 + componentConfiguration['build_number'] as Integer
       componentConfiguration['build_number'] = buildNumber
       echo "Next build number for ${componentName}: ${buildNumber}"
     }
   }
   // If this is the first time building the component then add a new entry and start at build 0.
   else {
     echo "Did not find build configuration for ${componentName}. Starting at Build # 0."
     configurationMap.repositories[componentName] = ['build_number': buildNumber]
   }

   def configurationJSON = readJSON text: JsonOutput.toJson(configurationMap)


   // Write updated build configuration to JSON file and then convert it back to TOML.
   writeJSON file: configurationJsonFileName, json: configurationJSON, pretty: 4

   // Use batch file to invoke Python script for JSON-TOML conversion.
   bat "commonbuild\\resources\\configUpdate.bat $configurationJsonFileName"

   return buildNumber
}
