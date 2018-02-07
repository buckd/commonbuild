package ni.vsbuild.packages

class Nipkg extends AbstractPackage {

   def releaseVersion
   def stagingPath
   
   Nipkg(script, packageInfo, payloadDir) {
      super(script, packageInfo, payloadDir)
      this.releaseVersion = packageInfo.get('release_version')
      this.stagingPath = packageInfo.get('install_destination')
   }

   void buildPackage(lvVersion) {
      def packageInfo = """
         Building package $name from $payloadDir
         Package version: $releaseVersion
         Staging path: $stagingPath
         LabVIEW/VeriStand version: $lvVersion
         """.stripIndent()
      
      script.buildNipkg(payloadDir, releaseVersion, stagingPath, lvVersion)
      script.echo packageInfo
   }
}

