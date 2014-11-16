require 'Class'

Queue = Class({
  elements = {},
  size = function(self)
    return #(self.elements)
  end,
  enqueue = function(self, element)
    table.insert(self.elements, element)
  end,
  dequeue = function(self)
    return table.remove(self.elements, 1)
  end, 
  peek = function(self)
    return self.elements[1]
  end,
  isEmpty = function(self)
    return self:size() == 0
  end,
  toString = function(self)
    return table.concat(self.elements, "\n")
  end,
  get = function(self, index)
    return self.elements[index]
  end,
  clear = function(self)
    self.elements = {}
  end
})