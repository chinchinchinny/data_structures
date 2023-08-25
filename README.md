# data_structures
Java class UndirectedMatrixGraph represents an undirected graph using an adjacency list. This allows you to create and manipulate graphs, add nodes and edges, remove nodes and edges, find the shortest distance between two nodes, and print the graph's adjacency list. The structure of this class is intended to handle undirected graphs.

Key Mechanism:
1. The class defines inner classes `Node` and `Edge` to represent nodes and edges in the graph, where each node contains information about its label, adjacent nodes, and incident edges.

2. You can add nodes to the graph using the `addNode` method and add edges between nodes using the `addEdge` method. Edges are bidirectional in an undirected graph, so when you add an edge between two nodes, it's added in both directions.

3. You can remove nodes from the graph using the `removeNode` method, and you can remove edges between nodes using the `removeEdge` method. Removing a node also removes its incident edges.

4. The `areAdjacent` method allows you to check if two nodes are adjacent (connected by an edge).

5. The `getShortestDistance` method calculates the shortest distance between two nodes using Dijkstra's algorithm. It also returns the shortest path as a list of node labels.

6. **Printing the Graph**: The `toString` method prints the adjacency list of the graph, and the `printEdges` method prints a list of edges with their weights for each node.

7. The `main` method provides an example of how to use this class to create a graph, add nodes and edges, and calculate the shortest distance between two nodes.

__________________________________________________________________________________________________________________________________________________________________________________________________________________________
Output:
1 -> [2, 3, 6]
2 -> [1, 3, 4]
3 -> [1, 2, 6, 4]
4 -> [2, 3, 5]
5 -> [4, 6]
6 -> [1, 3, 5]

13
[1, 3, 6, 5, 4]
