package Lesson_07;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(10);

//        graph.addEdge(1, 2);
//        graph.addEdge(0, 4);
//        graph.addEdge(1, 4);
//        graph.addEdge(3, 4);
//        graph.addEdge(7, 8);
//        graph.addEdge(2, 3);

//        System.out.println(graph.getAdjList(1));

//        DepthFirstPaths dfp = new DepthFirstPaths(graph, 2);
//        System.out.println(dfp.hasPathTo(3));
//        System.out.println(dfp.pathTo(3));
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);
        graph.addEdge(9, 0);

        graph.addEdge(1, 3);
        graph.addEdge(3, 5);
        graph.addEdge(5, 7);
        graph.addEdge(7, 9);
        graph.addEdge(9, 1);

        BreadthFirstPath bfp = new BreadthFirstPath(graph,0);
        System.out.println(bfp.hasPathTo(8));
        System.out.println(bfp.hasPathTo(3));
        System.out.println(bfp.pathTo(5));

        Graph graph1 = new Graph(10);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 9);
        graph1.addEdge(1, 2);
        graph1.addEdge(1, 3);
        graph1.addEdge(2, 4);
        graph1.addEdge(3, 4);
        graph1.addEdge(3, 6);
        graph1.addEdge(4, 7);
        graph1.addEdge(6, 7);
        graph1.addEdge(6, 8);
        graph1.addEdge(9, 7);
        graph1.addEdge(9, 8);
        BreadthFirstPath bfp1 = new BreadthFirstPath(graph1,0);
        System.out.println(bfp1.pathTo(7));
    }
}
