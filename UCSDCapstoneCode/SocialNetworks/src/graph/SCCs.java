/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Annah
 * 
 * A class that takes in a parent map and finds the Strongly Connected
 * Components
 *
 */

public class SCCs{
	HashMap<Integer,HashSet<Integer>> parentMap;
	HashSet<Integer> visited;
	Stack<Integer> finished;
	Stack<Integer> vertices;
	HashMap<Integer,HashSet<Integer>> parentMapTransposed;
	
	public SCCs() {
		parentMap = new HashMap<Integer,HashSet<Integer>>();
		parentMapTransposed = new HashMap<Integer,HashSet<Integer>>();
		finished = new Stack<Integer>();
		vertices = new Stack<Integer>();
	}
	
	public void setParentMap(HashMap<Integer,HashSet<Integer>> map) {
		parentMap = map;
		for(Integer vertex : parentMap.keySet()) {
			vertices.push(vertex);
		}
		
	}
	
	private void getTranspose() {
		for(Integer vertex : parentMap.keySet()) {
			for(Integer neighbor : parentMap.get(vertex)) {
				if(!parentMapTransposed.containsKey(neighbor)) {
					HashSet<Integer> set = new HashSet<Integer>();
					set.add(vertex);
					parentMapTransposed.put(neighbor, set);
				}
				else {
					parentMapTransposed.get(neighbor).add(vertex);
				}
			}
		}
	}
	
	public List<Graph> DFS(){
		visited = new HashSet<Integer>();
		while(vertices.size() != 0) {
			Integer v = vertices.pop();
			if(!visited.contains(v)) {
				DFSvisit(v);
			}
		}
		
		//at this point, finished is populated.
		//next is to take the transpose of the parentMap
		getTranspose();
		
		visited = new HashSet<Integer>();
		List<HashSet<Integer>> listOfSCCs = new ArrayList<HashSet<Integer>>();
		while(finished.size() != 0) {
			Integer v = finished.pop();
			if(!visited.contains(v)) {
				HashSet<Integer> set = new HashSet<Integer>();
				listOfSCCs.add(DFSvisitT(v, set));
			}
		}
		
		//at this point, we have a list of strongly connected components
		//we need to build a graph
		List<Graph> sccGraphs= new ArrayList<Graph>();
		for(HashSet<Integer> currSet : listOfSCCs) {
			sccGraphs.add(buildGraph(currSet));
		}
		
		
		
		return sccGraphs;
	}
	
	private Graph buildGraph(HashSet<Integer> scc) {
		Graph subGraph = new CapGraph();
		for(Integer node : scc) {
			subGraph.addVertex(node);
		}
		for(Integer node : subGraph.exportGraph().keySet()) {
			HashSet<Integer> neighbors = parentMap.get(node);
			for(Integer neighbor : neighbors) {
				if(scc.contains(neighbor)) {
					subGraph.addEdge(node, neighbor);
				}
			}
		}
		
		return subGraph;
	}
	
	private HashSet<Integer> DFSvisitT(Integer v, HashSet<Integer> set) {
		visited.add(v);
		set.add(v);
		if(parentMapTransposed.containsKey(v)) {
			for(Integer n : getTNeighbors(v)) {
				if(!visited.contains(n)) {
					DFSvisitT(n,set);
				}
			}
		}
		return set;
	}
	
	private void DFSvisit(Integer v) {
		visited.add(v);
		for(Integer n : getNeighbors(v)) {
			if(!visited.contains(n)) {
				DFSvisit(n);
			}
		}
		finished.push(v);
	}
	
	private HashSet<Integer> getTNeighbors(Integer v){
		return parentMapTransposed.get(v);
	}
	
	private HashSet<Integer> getNeighbors(Integer v) {
		return parentMap.get(v);
	}
	
}