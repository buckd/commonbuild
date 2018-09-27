def call(){

   echo "Fetching list of installed NI Packages."

   //Use NI Package Managet CLI to get list of installed NI Packages.
   bat """call python.exe commonbuild\\resources\\addRemoveNipkg.py list-installed
   exit %ERRORLEVEL%"""

   def installedNipkgList = readFile "nipm_version_manifest"

   //Add NIPM output to build log.
   def buildLog = readFile 'build_log'
   writeFile file: 'build_log', text: buildLog+"\r\n"+installedNipkgList
}
