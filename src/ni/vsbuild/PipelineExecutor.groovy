package ni.vsbuild

class PipelineExecutor implements Serializable {

   static void execute(script, BuildInformation buildInformation) {
      buildInformation.printInformation(script)
      
      if(buildInformation.packageType == PackageType.NIPM) {
         pipeline = nipm.Pipeline.builder(script, buildInformation).buildPipeline().execute()
      } else {
         script.currentBuild.result = "FAILURE"
         script.error "Build failed: PackageType ${buildInformation.packageType} is not supported."
      }
   }
}
