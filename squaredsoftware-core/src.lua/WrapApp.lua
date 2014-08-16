local applicationListener = "com.badlogic.gdx.ApplicationListener"

local wrapApp
local createBlankApp

wrapApp = function(xAppTable)
	local gdxApplicationAdapter = {
		create  = function() 				xAppTable.create() 				  end,
		render  = function() 				xAppTable.render()				  end,
		resize  = function(xWidth, xHeight) xAppTable.resize(xWidth, xHeight) end,
		pause   = function() 				xAppTable.pause()				  end,
		resume  = function() 				xAppTable.resume()				  end,
		dispose = function() 				xAppTable.dispose()				  end,
	}
	
	return luajava.createProxy(applicationListener, gdxApplicationAdapter)
end

createBlankApp = function() 
	local blankApp = {
		create  = function() 				end,
		render  = function() 				end,
		resize  = function(xWidth, xHeight) end,
		pause   = function() 				end,
		resume  = function() 				end,
		dispose = function() 				end,
	}
	
	return blankApp
end

WrapApp = {
	wrapApp        = wrapApp,
	createBlankApp = createBlankApp,
}