local executeCmd
local turnOnVerbose
local turnOffVerbose
local cd

local printIfIsVerbose
local isAbsolutePath

local isVerbose = false
local currentDir = ""

executeCmd = function(xCmd)
  local e
  local result
  e, result = os.execute("cd " .. currentDir .. "; " .. xCmd)
  printIfIsVerbose(xCmd, result, e)
  return result, e
end

turnOnVerbose  = function() isVerbose = true end
turnOffVerbose = function() isVerbose = false end

cd = function(xDir)
  local result
  local errorCode
  result, errorCode = executeCmd( "cd " .. xDir )
  if(errorCode ~= 0) then
    if(isAbsolutePath(xDir)) then
      currentDir = xDir
    else 
      currentDir = currentDir .. "/" .. xDir
    end
  else
    error('Not a valid directory')
  end
end

printIfIsVerbose = function(xCmd, xResult, xErrorCode)
  if isVerbose then 
    print("Cmd        : ", xCmd) 
    print("Result     : ", xResult)
    print("Error Code : ", xErrorCode)
  end
end

isAbsolutePath = function(xDir)
  return xDir:find("/") == 1 or xDir:find("~") == 1
end

Execute = {
  executeCmd                      = executeCmd,
  turnOnVerbose                   = turnOnVerbose,
  turnOffVerbose                  = turnOffVerbose,
  cd                              = cd,
}
