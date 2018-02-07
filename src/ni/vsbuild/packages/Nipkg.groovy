package ni.vsbuild.packages

class Nipkg extends AbstractPackage {

   def releaseVersion
   
   Nipkg(script, packageInfo, payloadDir) {
      super(script, packageInfo, payloadDir)
      this.releaseVersion = packageInfo.get('release_version')
   }

   void buildPackage(lvVersion) {
      def packageInfo = """
         Building package $name from $payloadDir
         Package version: $releaseVersion
         LabVIEW/VeriStand version: $lvVersion
         """.stripIndent()
      
      script.buildNipkg(payloadDir, releaseVersion, lvVersion)
      script.echo packageInfo
   }
}
