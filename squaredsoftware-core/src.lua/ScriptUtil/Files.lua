require("ScriptUtil/Execute")

local doesDirectoryExist
local doesFileExist
local replaceStringWithNewStringInFile

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

Files = {
  doesDirectoryExist               = doesDirectoryExist,
  doesFileExist                    = doesFileExist,
  replaceStringWithNewStringInFile = replaceStringWithNewStringInFile,
}