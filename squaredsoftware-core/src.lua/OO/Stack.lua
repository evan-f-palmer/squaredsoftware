require 'Class'

Stack = Class({
  elements = {},
  size = function(self)
    return #(self.elements)
  end,
  push = function(self, element)
    table.insert(self.elements, element)
  end,
  pop = function(self)
    return table.remove(self.elements)
  end, 
  peek = function(self)
    return self.elements[self:size()]
  end,
  isEmpty = function(self)
    return self:size() == 0
  end,
  toString = function(self)
    return table.concat(self.elements, "\n")
  end,
  get = function(self, index)
    return self.elements[self:size() - index + 1]
  end,
  clear = function(self)
    self.elements = {}
  end
})
