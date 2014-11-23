require 'ObjectOriented/Class'

Iterator = Class({
  object = {},
  index  = 1,
  next = function(self) 
    local previousElement = self:current()
    self.index = self.index + 1
    return previousElement
  end,
  current = function(self)
    return self.object:get(self.index)
  end,
  isFinished = function(self)
    return self.index > self.object:size()
  end
})

-- myQueue = Queue()
-- QueueIterator = Iterator({object = myQueue})