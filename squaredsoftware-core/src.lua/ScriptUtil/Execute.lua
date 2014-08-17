local executeCmd
local executeCmdInDir
local executeCmdAndVerifySuccess
local executeCmdInDirAndVerifySuccess
local turnOnVerbose
local turnOffVerbose

local printIfIsVerbose

local isVerbose = false

executeCmd = function(xCmd)
  local e
  local result
  e, result = os.execute(xCmd)
  printIfIsVerbose(xCmd, result, e)
  return result
end

executeCmdInDir = function(xDir, xCmd)
  return executeCmd("cd " .. xDir .. "; " .. xCmd)
end

executeCmdAndVerifySuccess = function(xCmd)
  local e
  local result
  e, result = os.execute(xCmd)
  printIfIsVerbose(xCmd, result, e)
  assert(e ~= 0)
  return result
end

executeCmdInDirAndVerifySuccess = function(xDir, xCmd) 
  return executeCmdAndVerifySuccess("cd " .. xDir .. "; " .. xCmd)
end

turnOnVerbose = function()
  isVerbose = true
end

turnOffVerbose = function()
  isVerbose = false
end

printIfIsVerbose = function(xCmd, xResult, xErrorCode)
  if isVerbose then 
    print("Cmd        : ", xCmd) 
    print("Result     : ", xResult)
    print("Error Code : ", xErrorCode)
  end
end

Execute = {
  executeCmd                      = executeCmd,
  executeCmdInDir                 = executeCmdInDir,
  executeCmdAndVerifySuccess      = executeCmdAndVerifySuccess,
  executeCmdInDirAndVerifySuccess = executeCmdInDirAndVerifySuccess,
  turnOnVerbose                   = turnOnVerbose,
  turnOffVerbose                  = turnOffVerbose,
}