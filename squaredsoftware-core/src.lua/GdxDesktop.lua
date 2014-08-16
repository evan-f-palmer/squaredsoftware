local lwjglApp = luajava.bindClass("com.badlogic.gdx.backends.lwjgl.LwjglApplication")
local lwjglAppConfig = luajava.bindClass("com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration")

local start
local setApp
local getConfig

local config   = luajava.new(lwjglAppConfig);
local app = nil

start = function()
	assert(app ~= nil)
	luajava.new(lwjglApp, app, config)
end

setApp = function(xApp)
	app = xApp
end

getConfig = function()
	return config
end

GdxDesktop = {
	start     = start,
	setApp    = setApp,
	getConfig = getConfig,
}





