require 'Class'

Iterator = Class({
  object = {},
  index  = 1,
  next = function(self) 
    self.index = self.index + 1
  end,
  current = function(self)
    return self.object.get(self.index)
  end,
  isFinished = function(self)
    return self.index > self.object.size()
  end
})

-- myQueue = Queue()
-- QueueIterator = Iterator({object = myQueue})