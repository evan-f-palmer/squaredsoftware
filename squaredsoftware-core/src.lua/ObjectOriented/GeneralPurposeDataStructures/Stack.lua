require 'ObjectOriented/Class'

Stack = Class({
  elements = {},
  size = function(thisStack)
    return #(thisStack.elements)
  end,
  push = function(thisStack, element)
    table.insert(thisStack.elements, element)
  end,
  pop = function(thisStack)
    return table.remove(thisStack.elements)
  end, 
  peek = function(thisStack)
    return thisStack.elements[thisStack:size()]
  end,
  isEmpty = function(thisStack)
    return thisStack:size() == 0
  end,
  toString = function(thisStack)
    return table.concat(thisStack.elements, "\n")
  end,
  get = function(thisStack, index)
    return thisStack.elements[thisStack:size() - index + 1]
  end,
  clear = function(thisStack)
    thisStack.elements = {}
  end
})
