require("ScriptUtil/Execute")
require("ScriptUtil/OS")

local doesDirectoryExist
local doesFileExist
local replaceStringWithNewStringInFile
local scanDirectory

doesFileExist = function(xName)
  local f = io.open(xName, "r")
  if f ~= nil then
    io.close(f) 
    return true 
  else 
    return false 
  end
end

doesDirectoryExist = function(xDir)
  return os.execute( "cd " .. xDir )
end

replaceStringWithNewStringInFile = function(xOldString, xNewString, xFilePath)
  Execute.executeCmd("sed -i \'s/" .. xOldString .. "/" .. xNewString .. "/g\' " .. xFilePath)
end

scanDirectory = function(xDir)
    local fileTable, popen = {}, io.popen
    for filename in popen(OS.goto(xDir)):lines() do
        table.insert(fileTable, filename)
    end
    return fileTable
end

Files = {
  doesDirectoryExist               = doesDirectoryExist,
  doesFileExist                    = doesFileExist,
  replaceStringWithNewStringInFile = replaceStringWithNewStringInFile,
  scanDirectory                    = scanDirectory,
}
