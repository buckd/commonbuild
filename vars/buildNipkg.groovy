def call(payloadDir, releaseVersion, stagingPath, lvVersion) {
   
   def controlFields = readProperties file: "control"
   def basePackageName = "${controlFields.get('Package')}"
   def packageName = basePackageName.replaceAll("\\{version\\}", "${lvVersion}")
   
   echo "Building ${packageName} with control attributes:"
   echo controlFields.toMapString()

   def controlFileText = readFile "control"
   echo controlFileText

   def newControlFileText = controlFileText.replaceAll("\\{version\\}", "${lvVersion}")
   
   def newStagingPath = stagingPath.replaceAll("\\{version\\}", "${lvVersion}")
   echo "Staging path: $newStagingPath"

   bat "(robocopy \"${payloadDir}\" \"nipkg\\${packageName}\\data\\${newStagingPath}\" /MIR /NFL /NDL /NJH /NJS /nc /ns /np) ^& exit 0"

   writeFile file: "nipkg\\${packageName}\\debian-binary", text: "2.0"      
   writeFile file: "nipkg\\${packageName}\\control\\control", text: newControlFileText

}

