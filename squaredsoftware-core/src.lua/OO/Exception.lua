require 'Class'
----------------------------------------
--  Public Interface
----------------------------------------
Exception = Class({
  message      = "Exception",
  printMessage = function(self) print(self.message) end,
  throw        = function(self) error(self.message) end,
})
                     
----------------------------------------
--  Static Globals
---------------------------------------- 
NilReferenceException   = Exception({message = "Nil Reference Exception"})
FileNotFoundException   = Exception({message = "File Not Found"})
NotImplementedException = Exception({message = "Not Implemented"})

--NilReferenceException:throw()
--FileNotFoundException:throw()
--NotImplementedException:throw()
--NilReferenceException:printMessage()
--FileNotFoundException:printMessage()
--NotImplementedException:printMessage()
