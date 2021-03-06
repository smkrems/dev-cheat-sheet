package lin.louis.game.common.pathfinder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import lin.louis.game.common.board.MatrixBooleanBoard;
import lin.louis.game.common.command.FourDirection;
import lin.louis.game.common.Point;

public class BreadthFirstSearch {
    public static Node bfs(MatrixBooleanBoard walls, Point start, Point end) {
        Node goal = computeGoalNode(walls, start, end);
        if (goal != null) {
            Stack<Node> nodeStacks = new Stack<>();
            while(!goal.p.equals(start)) {
                if (goal.direction != null) {
                    nodeStacks.add(goal);
                }
                goal = goal.previous;
            }
            Node finalNode = nodeStacks.pop();
            Node tmp = finalNode;
            while(!nodeStacks.isEmpty()) {
                Node next = nodeStacks.pop();
                next.previous = tmp;
                tmp.next = next;
                tmp = tmp.next;
            }
            return finalNode;
        }
        return null;
    }

    public static FourDirection[] bfsForDirections(MatrixBooleanBoard walls, Point start, Point end) {
        Node goal = computeGoalNode(walls, start, end);
        if (goal != null) {
            int size = 0;
            Stack<Node> nodeStacks = new Stack<>();
            while(!goal.p.equals(start)) {
                size++;
                if (goal.direction != null) {
                    nodeStacks.add(goal);
                }
                goal = goal.previous;
            }
            FourDirection[] moves = new FourDirection[size];
            int index = 0;
            while(!nodeStacks.isEmpty()) {
                Node next = nodeStacks.pop();
                moves[index] = next.direction;
                index++;
            }
            return moves;
        }
        return null;
    }

    private static Node computeGoalNode(MatrixBooleanBoard walls, Point start, Point end) {
        MatrixBooleanBoard maze = walls.copy();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start));
        Node current;
        List<Node> goalList = new ArrayList<>();
        while (!queue.isEmpty()) {
            current = queue.remove();
            for (FourDirection direction : FourDirection.values()) {
                Point p = current.p.add(direction);
                if (!maze.isOutOfRange(p) && !maze.isBlocked(p)) {
//                    current.next = new Node(p, current.direction != null ? current.direction : direction);
                    current.next = new Node(p, direction);
                    current.next.previous = current;
                    current.next.length = current.length + 1;

                    queue.add(current.next);
                    maze.set(p, false);
                }
            }
            if (current.p.x == end.x && current.p.y == end.y) {
                goalList.add(current);
            }
        }

        if (!goalList.isEmpty()) {
            Node goal = goalList.get(0);
            for (Node possibleGoal : goalList) {
                if (possibleGoal.length < goal.length) {
                    goal = possibleGoal;
                }
            }
            return goal;
        }
        // FAILLLLL!!!! t(-_-t)
        return null;
    }

    public static class Node {
        public Point p;
        public Node next;
        public Node previous;
        public FourDirection direction;
        public int length;

        public Node(Point p) {
            this.p = p;
            this.length = 1;
        }

        public Node(Point p, FourDirection direction) {
            this(p);
            this.direction = direction;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node current = this;
            while(current != null) {
                sb.append(current.direction).append("-");
                current = current.next;
            }
            return sb.toString();
        }
    }
}
