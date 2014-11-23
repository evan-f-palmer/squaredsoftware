require 'Class'

local function containsVertex(edge, VERTEX) return edge.A == VERTEX or edge.B == VERTEX end
local function setWeight(edge, WEIGHT)      edge.weight = WEIGHT                        end
local function setVertices(edge, A, B)      edge.A = A; edge.B = B;                     end

Edge = Class()
Edge.isDirected     = function() return true end
Edge.containsVertex = containsVertex
Edge.setWeight      = setWeight
Edge.setVertices    = setVertices
Edge.setNodes       = setVertices

BiDirectionalEdge = Edge() 
BiDirectionalEdge.isDirected = function() return false end

-- Edge({A = A, B = B, weight = WEIGHT})
-- BiDirectionalEdge({A = A, B = B, weight = WEIGHT})
