----------------------------------------
--  Public Interface
----------------------------------------
Class = {
  new = function(self, subclass) 
      return newInstance(self, subclass)
  end,
  
  fusion = function(self, ...)
      return fuse({...}, newInstance(self))
  end
}

----------------------------------------
--  OO Static Globals
---------------------------------------- 
function PureVirtualFunction() 
    error('Pure Virtual Functions cannot be executed.') 
end

function inherit(subclass, baseclass)
    setmetatable(subclass, {__index = baseclass})
end

function newInstance(baseclass, construct)
    local newObject = construct or {}
    inherit(newObject, baseclass)
    return newObject
end

function fuse(...)
    local args = {...}; local fusedObject = {}
    for index = 1, #args do
        fusedObject = inherit(args[index], fusedObject)
    end
    return fusedObject
end