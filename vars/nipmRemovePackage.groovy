def call(packagePath) {

   echo "Using NI Package Manager to remove $packagePath"
   bat """call python.exe commonbuild\\resources\\installNipkg.py uninstall \"${packagePath}\"
   exit %ERRORLEVEL%"""
}
