def call(releaseVersion, payloadDir, lvVersion) {
   
   def controlFields = readProperties file: "control"
   
   echo controlFields.toMapString()

   def controlFileText = new File("control")

   echo controlFileText

   def newControlFileText = controlFileText
   def newControlFile = new File("nipkg\\control")
   
   newControlFile.text = newControlFileText

}
