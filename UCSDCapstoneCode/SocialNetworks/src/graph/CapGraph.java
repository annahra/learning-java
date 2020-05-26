/**
 * 
 */
package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * @author Annah
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {
	
	private HashMap<Integer, HashSet<Integer>> graph;
	
	public CapGraph() {
		graph = new HashMap<Integer, HashSet<Integer>>();
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		if(!graph.containsKey(num)) {
			HashSet<Integer> set = new HashSet<Integer>();
			graph.put(num,set);
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		if(graph.containsKey(from)) {
			graph.get(from).add(to);
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		Graph newGraph = new CapGraph();
		if(!graph.containsKey(center)) {
			return newGraph;
		}
		else {
			newGraph.addVertex(center);
			HashSet<Integer> mainNeighbors = graph.get(center);
			for(Integer neigh : mainNeighbors) {
				newGraph.addVertex(neigh);
				newGraph.addEdge(center, neigh);
				HashSet<Integer> neighNeigh = graph.get(neigh);
				for(Integer possNeigh : neighNeigh) {
					if(mainNeighbors.contains(possNeigh)) {
						newGraph.addEdge(neigh, possNeigh);
					}
				}
			}
		}
		return newGraph;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		SCCs scc = new SCCs();
		scc.setParentMap(graph);
		
		
		return scc.DFS();
	} 
	

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		HashMap<Integer,HashSet<Integer>> holder = graph;
		return holder;
	}

}
