package Lesson_06;

public class MyTreeMap<Key extends Comparable<Key>, Value> {
    Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int size;
        int height;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            size = 1;
            height = 0;
        }
    }

    private class Height {
        int height = 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    private int getHeight(Node node, Key key, int level)  {
        if (node == null)
            return 0;
        if (node.key == key)
            return level;
        int downlevel = getHeight(node.left, key, level + 1);
        if (downlevel != 0)
            return downlevel;
        downlevel = getHeight(node.right, key, level + 1);
        return downlevel;
    }

    public int getHeight(Key key) {
        return getHeight(root, key, 0);
    }

    boolean isBalanced(){
        return isBalanced(root,new Height());
    }

    private boolean isBalanced(Node root, Height height) {
        if (root == null) { 
            height.height = 0;
            return true;
        } 

        Height lheight = new Height(), rheight = new Height(); 
        boolean l = isBalanced(root.left, lheight); 
        boolean r = isBalanced(root.right, rheight); 
        int lh = lheight.height, rh = rheight.height;
        height.height = (Math.max(lh, rh)) + 1;

        if ((lh - rh >= 2) || (rh - lh >= 2)) {
            return false;
        } else {
            return l && r;
        }
    } 
	
    public boolean isEmpty() {
        return root == null;
    }

    private boolean isKeyNotNull(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key не должен быть null");
        }
        return true;
    }

    public boolean contains(Key key) {
        return get(root, key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    int maxDepth(Node node) {
        if (node == null) {
            return 0;
        } else {
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

    private Value get(Node node, Key key) {
        isKeyNotNull(key);
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    private Node getNode(Node node, Key key) {
        isKeyNotNull(key);
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    public void put(Key key, Value value) {
        isKeyNotNull(key);
        if (value == null) {
            //delete(key)
            return;
        }
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
		node.height = getHeight(key);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Key minKey() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public Key maxKey() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
		node.height = getHeight(node.key);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(Key key) {
        isKeyNotNull(key);
        delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node temp = node;
            node = min(node.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;

        }
		node.height = getHeight(node.key);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }


    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node node) {
        if (node == null) {
            return "";
        }
        return toString(node.left) + " " +
                node.key.toString() + " = " + node.value.toString() +
                ", " + toString(node.right);
    }
}
