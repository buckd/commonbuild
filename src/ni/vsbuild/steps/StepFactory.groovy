package ni.vsbuild.steps

class StepFactory implements Serializable {

   static List<Step> createSteps(script, stepList, lvVersion) {
      List<Step> steps = []
      def mapSteps = stepList.get('steps')
      for (def mapStep : mapSteps) {
         Step step = StepFactory.createStep(script, mapStep, lvVersion)
         steps.add(step)
      }

      return steps
   }

   static Step createStep(script, mapStep, lvVersion) {
      def type = mapStep.get('type')

      if(type == 'lvBuildAll') {
         return new LvBuildAllStep(script, mapStep, lvVersion)
      }

      if(type == 'lvBuildSpec') {
         return new LvBuildSpecStep(script, mapStep, lvVersion)
      }

      if(type == 'lv64BuildSpec') {
         return new Lv64BuildSpecStep(script, mapStep, lvVersion)
      }

      if(type == 'lvBuildSpecAllTargets') {
         return new LvBuildSpecAllTargetsStep(script, mapStep, lvVersion)
      }

      if(type == 'lvRunVi') {
         return new LvRunViStep(script, mapStep, lvVersion)
      }

      if(type == 'lvSetConditionalSymbol') {
         return new LvSetConditionalSymbolStep(script, mapStep, lvVersion)
      }

      if(type == 'vsStepsUpdateProjectFiles') {
         return new VsStepsUpdateProjectFiles(script, mapStep, lvVersion)
      }

      if(type == 'Test') {
         return new IntegrationTest(script, mapStep, lvVersion)
      }

      if(type == 'githubRelease') {
         return new GitHubRelease(script, mapStep, lvVersion)
      }

      script.failBuild("Type \'$type\' is invalid for step \'${mapStep.get('name')}\'.")
   }
}
