local newInstance
local inherit

Class = {}
function Class:new(construct)
  return newInstance(self, construct) 
end
function Class:override__call(callFunction)
  getmetatable(self).__call = callFunction 
end
function Class:override__add(addFunction)
  getmetatable(self).__add = addFunction 
end
function Class:override__concat(concatFunction)
  getmetatable(self).__concat = concatFunction 
end
function Class:override(toOverride, newFunction)
  self[toOverride] = newFunction
end
setmetatable(Class, { 
  __call   = newInstance, 
  __add    = newInstance, 
  __concat = newInstance 
})

----------------------------------------
--  OO Static Globals
---------------------------------------- 

Object = Class

super = function(object)
  return getmetatable(object).__index
end

----------------------------------------
--  Support
----------------------------------------

function newInstance(baseclass, construct)
  return inherit(construct or {}, baseclass)
end

function inherit(subclass, baseclass)
  return setmetatable(subclass,   { 
         __index  = baseclass, 
         __call   = baseclass.new, 
         __add    = baseclass.new, 
         __concat = baseclass.new })
end 
