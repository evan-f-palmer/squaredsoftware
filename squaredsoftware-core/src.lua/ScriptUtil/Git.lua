require("ScriptUtil/Execute")

local isLocalRepoCommitIdTheSameAsTip
local getLocalCommitId
local getTipCommitId
local clone
local cloneTo
local cloneSquaredSoftwareRepoTo

local squaredSoftwareRepoName        = "git@github.com:evan-f-palmer/squaredsoftware.git"

isLocalRepoCommitIdTheSameAsTip = function(xDir)
    Execute.cd(xDir)
    return getLocalCommitId() == getTipCommitId()
end

getLocalCommitId = function()
    return Execute.executeCmd("git rev-parse HEAD")
end

getTipCommitId = function(xDir)
    return Execute.executeCmd("git rev-parse origin/master")
end

clone = function(xReponame)
    Execute.executeCmd("git clone " .. xReponame)
end

cloneTo = function(xReponame, xDir)
    Execute.executeCmd("git clone " .. xReponame .. " " .. xDir)
end

cloneSquaredSoftwareRepoTo = function(xDir)
    cloneTo(squaredSoftwareRepoName, xDir)
end

Git = {
  isLocalRepoCommitIdTheSameAsTip = isLocalRepoCommitIdTheSameAsTip,
  getLocalCommitId                = getLocalCommitId,
  getTipCommitId                  = getTipCommitId, 
  clone                           = clone,
  cloneTo                         = cloneTo,
  cloneSquaredSoftwareRepoTo      = cloneSquaredSoftwareRepoTo,
}