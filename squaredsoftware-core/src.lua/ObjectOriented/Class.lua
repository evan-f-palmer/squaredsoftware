local newInstance
local inherit
local override__call
local override__add
local override__concat
local override

function newInstance(baseclass, construct)
  return inherit(construct or {}, baseclass)
end

function inherit(subclass, baseclass)
  return setmetatable(subclass, { 
           __index  = baseclass, 
           __call   = baseclass.new, 
           __add    = baseclass.new, 
           __concat = baseclass.new 
         })
end 

function override__call(object, callFunction)
  getmetatable(object).__call = callFunction 
end
function override__add(object, addFunction)
  getmetatable(object).__add = addFunction 
end
function override__concat(object, concatFunction)
  getmetatable(object).__concat = concatFunction 
end
function override(object, toOverride, newFunction)
  object[toOverride] = newFunction
end

----------------------------------------
--  OO Static Globals
---------------------------------------- 

super = function(object)
  return getmetatable(object).__index
end

Class = {
  new               = newInstance,
  override__call    = override__call,
  override__add     = override__add,
  override__concat  = override__concat,
  override          = override,
}
setmetatable(Class, { 
  __call   = newInstance, 
  __add    = newInstance, 
  __concat = newInstance 
})

Object = Class