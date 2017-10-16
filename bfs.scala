package algorithm;

import scala.collection.mutable.ListBuffer;

import ds._;

object bfs {    
    def execute_iter(g: Graph[Int, Int, Int])(source: g.Node) : List[g.Node] = {
        
        implicit class RichNode(node : g.Node) {                               
            if(!node.prop.contains("visited")) node.prop += ("visited" -> false);                                                    
            def visited() : Boolean = node.prop("visited").asInstanceOf[Boolean]                        
            def visit(value : Boolean) = node.prop += ("visited" -> value);            
        }    

        var result: ListBuffer[g.Node] = ListBuffer()
        var visited: ListBuffer[g.Node]  = ListBuffer()
        visited += source

        while(visited.length > 0) {                   
            val head: g.Node = visited(visited.length - 1)        
            if(!head.visited) {
                head.visit(true)             
                result += head                
                visited = visited.dropRight(1)                        
                for(node <- g.getNeighbors(head) if !node.visited) {
                    visited += node         
                }
            }                                                    
        }
        return result.toList
  }
}