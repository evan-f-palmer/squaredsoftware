require 'Class'

Queue = Class({
  front = 1, 
  elements = {},
  size = function(self)
    return #(self.elements) - self.front + 1
  end,
  enqueue = function(self, element)
    table.insert(self.elements, element)
  end,
  dequeue = function(self)
    self.front = self.front + 1
    return table.remove(self.elements, self.front - 1)
  end, 
  peek = function(self)
    return self.elements[self.front]
  end,
  isEmpty = function(self)
    return self:size() == 0
  end,
  toString = function(self)
    return table.concat(self.elements, "\n")
  end,
  get = function(self, index)
    return self.elements[self.front + index - 1]
  end
})