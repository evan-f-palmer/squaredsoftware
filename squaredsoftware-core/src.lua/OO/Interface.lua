require 'Class'

local newInterface
local combineInterfaces

Interface = {}

function Interface:new(methodNameArray)
  return newInterface(methodNameArray)
end

setmetatable(Interface, { 
  __call   = newInterface, 
  __add    = combineInterfaces, 
  __concat = combineInterfaces 
})

----------------------------------------
--  OO Static Globals
---------------------------------------- 

PureVirtualFunction = function() 
  error('Pure Virtual Functions cannot be executed.') 
end

----------------------------------------
--  Support
----------------------------------------

function newInterface(methodNameArray)
  local pureTable = {}
  for i=1, #methodNameArray do
    pureTable[methodNameArray[i]] = PureVirtualFunction
  end
  return Class:new(pureTable)
end

function combineInterfaces(A, B)
  B = B or {}
  for i=1, #A do B[#B+1] = A[i] end
  return B
end