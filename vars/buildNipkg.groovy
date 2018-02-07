def call(releaseVersion, payloadDir, lvVersion) {
   
   def controlFields = readProperties file: "control"
   
   echo controlFields.toMapString()

   def controlFileText = readFile "control"

   echo controlFileText

   def newControlFileText = controlFileText
   
   writeFile file: "nipkg\\control", text: newControlFileText

}
