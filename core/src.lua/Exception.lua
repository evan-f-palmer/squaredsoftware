require 'Class'
----------------------------------------
--  Public Interface
----------------------------------------
Exception = Class:new({
                       message = "Exception",
                       printMessage = function(self) print(self.message) end,
                       throw = function(self) error(self.message) end 
                     })
                     
----------------------------------------
--  Static Globals
---------------------------------------- 
NilReferenceException   = newInstance(Exception, {message = "Nil Reference Exception"})
FileNotFoundException   = Exception:new({message = "File Not Found"})
NotImplementedException = Exception:new({message = "Not Implemented"})

--NilReferenceException:throw()
--FileNotFoundException:throw()
--NotImplementedException:throw()
--NilReferenceException:printMessage()
--FileNotFoundException:printMessage()
--NotImplementedException:printMessage()