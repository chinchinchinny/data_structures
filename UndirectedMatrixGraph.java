import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
public class UndirectedMatrixGraph {
private Map<String, Node> nodes = new HashMap<>();
private class Node {
private String label;
private List<Node> adjacentNodes = new ArrayList<>();
private List<Edge> incidentEdges = new ArrayList<>();
public Node(String label) {
this.label = label;
}
public void addEdge(Node theOther, int weight) {
if (!adjacentNodes.contains(theOther)) {
adjacentNodes.add(theOther);
incidentEdges.add(new Edge(this, theOther, weight));
}
}
public void removeEdge(Node theOther) {
Edge edgeToRemove = null;
for (Edge edge : incidentEdges) {
if (edge.endOne == this && edge.endTwo == theOther ||

edge.endTwo == this && edge.endOne == theOther) {

edgeToRemove = edge;
break;

}
}
adjacentNodes.remove(theOther);
incidentEdges.remove(edgeToRemove);
}
public List<Node> getAdjacentNodes() {
return adjacentNodes;
}
public List<Edge> getInsidentEdges() {
return incidentEdges;
}
@Override
public String toString() {
return label;
}
}
private class Edge {
private Node endOne;
private Node endTwo;
private int weight;
public Edge(Node endOne, Node endTwo, int weight) {
this.endOne = endOne;
this.endTwo = endTwo;
this.weight = weight;
}
@Override
public String toString() {
return "{" + endOne + "," + endTwo + ":w=" + weight + "}";
}
}
/**
* Returns a collection of all nodes in the graph.
*

* @return a collection of nodes
*/
private Collection<Node> getAllNodes() {
return nodes.values();
}
/**
* Adds a node to the graph.
*
* @param label is the label of the node to add
*/
public void addNode(String label) {
nodes.putIfAbsent(label, new Node(label));
}
/**
* Adds an edge to the graph.
*
* @param one is the label of the first node on the edge to add
* @param two is the label of the second node on the edge to add
* @param weight is the weight of the edge to add
*/
public void addEdge(String one, String two, int weight) {
Node nodeOne = nodes.get(one);
Node nodeTwo = nodes.get(two);
if (nodeOne == null || nodeTwo == null) {
throw new IllegalArgumentException("No such node exists!");
}
nodeOne.addEdge(nodeTwo, weight);
nodeTwo.addEdge(nodeOne, weight);
}
/**
* Deletes a node to from the graph.
*
* @param label is the label of the node to delete
*/

public void removeNode(String label) {
Node nodeToRemove = nodes.get(label);
if (nodeToRemove == null) {
return;
}
for (Node node : getAllNodes()) {
node.getAdjacentNodes().remove(nodeToRemove);
node.removeEdge(nodeToRemove);
}
nodes.remove(label);
}
/**
* Deletes an edge from the graph.
*
* @param one is the label of the first node on the edge to delete
* @param two is the label of the second node on the edge to delete
*/
public void removeEdge(String one, String two) {
Node nodeOne = nodes.get(one);
Node nodeTwo = nodes.get(two);
if (nodeOne == null || nodeTwo == null) {
return;
}
nodeOne.removeEdge(nodeTwo);
nodeTwo.removeEdge(nodeOne);
}
/**
* Finds is two nodes are adjacent.
*
* @param one is the label of the first node
* @param two is the label of the second node
*
* @return {@code true} if the nodes are adjacent, or {@code false} if
the nodes
* are not adjacent
*/

public boolean areAdjacent(String one, String two) {
Node nodeOne = nodes.get(one);
Node nodeTwo = nodes.get(two);
return nodeOne.getAdjacentNodes().contains(nodeTwo);
}
/**
* TO STRING (PRINTING THE GRAPH)
*/
/**
* Prints into a string the adjacency list of the graph.
*
* @return a string which contains the adjacency list of the graph
*/
@Override
public String toString() {
StringBuffer toPrint = new StringBuffer();
for (Node node : getAllNodes()) {
toPrint.append(node.label + " -> " + node.getAdjacentNodes() +

"\n");
}
return toPrint.toString();
}
/**
* For each node of the graph, prints a list of edges with their
weights.
*/
public void printEdges() {
for (Node node : getAllNodes()) {
System.out.println(node.label + " -> " +

node.getInsidentEdges());

}
}
private class WeightedNode {
private Node node;

private int weight;
public WeightedNode(Node node, int weight) {
this.node = node;
this.weight = weight;
}
}
public int getShortestDistance(String from, String to, List<String>
path) {

Node start = nodes.get(from);
Node finish = nodes.get(to);
if (start == null || finish == null) {
throw new IllegalArgumentException();
}
Map<Node, WeightedNode> weighted = new HashMap<>();
for (Node node : getAllNodes()) {
weighted.put(node, new WeightedNode(node, Integer.MAX_VALUE));
}
weighted.get(start).weight = 0;
Set<Node> visited = new HashSet<>();
Map<Node, Node> previousNodes = new HashMap<>();
Queue<WeightedNode> queue = new

PriorityQueue<>(Comparator.comparingInt(node -> node.weight));

queue.add(weighted.get(start));
while (!queue.isEmpty()) {
WeightedNode current = queue.remove();
if (visited.contains(current.node)) {
continue;
}
visited.add(current.node);
for (Edge edge : current.node.getInsidentEdges()) {
Node neighbor = edge.endTwo;
if (visited.contains(neighbor)) {
continue;
}

WeightedNode weightedNeighbor = weighted.get(neighbor);
int newDistance = current.weight + edge.weight;
int oldDistance = weightedNeighbor.weight;
if (newDistance < oldDistance) {
weightedNeighbor.weight = newDistance;
queue.add(weightedNeighbor);
previousNodes.put(neighbor, current.node);
}
}
}
Stack<Node> reversedPath = new Stack<>();
Node current = finish;
reversedPath.push(current);
while (previousNodes.get(current) != null) {
current = previousNodes.get(current);
reversedPath.push(current);
}
while (!reversedPath.isEmpty()) {
path.add(reversedPath.pop().label);
}
return weighted.get(finish).weight;
}
public static void main(String[] args) {
UndirectedMatrixGraph graph =
new UndirectedMatrixGraph();
for (int i = 1; i <= 6; i++) {
graph.addNode(Integer.toString(i));
}
graph.addEdge("2", "1", 7);
graph.addEdge("3", "1", 9);
graph.addEdge("6", "1", 14);
graph.addEdge("2", "3", 10);
graph.addEdge("2", "4", 15);
graph.addEdge("3", "6", 2);
graph.addEdge("3", "4", 11);
graph.addEdge("4", "5", 1);

graph.addEdge("5", "6", 1);
System.out.println(graph);
List<String> path = new ArrayList<>();
System.out.println(graph.getShortestDistance("1", "4", path));
System.out.println(path);

}
}