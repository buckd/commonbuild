package ni.vsbuild.packages

class Nipkg extends AbstractPackage {

   def releaseVersion
   
   Nipkg(script, packageInfo, payloadDir) {
      super(script, packageInfo, payloadDir)
      this.releaseVersion = packageInfo.get('release_version')
   }

   void buildPackage() {
      def packageInfo = """
         Building package $name from $payloadDir
         Package version: $releaseVersion
         """.stripIndent()
      
      script.buildNipkg(payloadDir, releaseVersion, lvVersion)
      script.echo packageInfo
   }
}
