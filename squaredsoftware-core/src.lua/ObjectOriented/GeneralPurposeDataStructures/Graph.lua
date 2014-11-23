require 'ObjectOriented/Class'
require 'GeneralPurposeDataStructures/Stack'
--require 'Edge'

local function add(elementtable, element)
  table.insert(elementtable, element)
end

local function remove(elementtable, element)
  table.remove(elementtable, element)
end

local function addVertex(graph, vertex)
  add(graph.vertices, vertex)
end

local function addEdge(graph, edge) 
  add(graph.edges, edge)
end

local function populateEdgeRemovalStack(edges, vertex)
  local toRemove = Stack()
  for _, edge in pairs(edges) do
    if edge:containsVertex(vertex) then
       toRemove:push(edge)
    end
  end
  return toRemove
end

local function removeVertex(graph, vertex)
  remove(graph.vertices, vertex)
  local toRemove = populateEdgeRemovalStack(graph.edges, vertex)
  while not toRemove:isEmpty() do
    graph:removeEdge(toRemove:pop())
  end 
end

local function removeEdge(graph, edge)
  remove(graph.edges, edge)
end

Graph = Class({
  
  vertices = {},
  edges    = {},
  
  addVertex    = addVertex,
  addNode      = addVertex,
  addEdge      = addEdge,
  removeVertex = removeVertex,
  removeNode   = removeVertex,
  removeEdge   = removeEdge,
  
})