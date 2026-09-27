import java.util.*;

public class Tree {
    // We recommend attempting this class last, as it hasn't been scaffolded for your team.
    // Even if your team doesn't have time to implement this class, it is a useful exercise
    // to think about how you might split up the work to get the Tree and TreeMultiSet
    // implemented.

    // root is not none or subtrees is not empty
    // subtrees is never null

    private Integer root;
    private List<Tree> subtrees;
    private Random random = new Random();

    public Tree(Integer root, List<Tree> subtrees) {
        this.root = root;
        this.subtrees = subtrees;
    }

    public Tree() {
        this(null, new ArrayList<>());
    }

    public Tree(int root) {
        this(root, new ArrayList<>());
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        } else {
            int s = 1;
            for (Tree subtree : subtrees) {
                s += subtree.size();
            }
            return s;
        }
    }

    public int count(int item) {
        if (isEmpty()) {
            return 0;
        } else {
            int n = 0;
            if (root == item) {
                n += 1;
            }

            for (Tree subtree : subtrees) {
                n += subtree.count(item);
            }
            return n;
        }
    }

    private String strIndented(int depth) {
        if (isEmpty()) {
            return "";
        } else {
            StringBuilder s = new StringBuilder(" ".repeat(depth) + root.toString() + "\n");
            for (Tree subtree : subtrees) {
                s.append(subtree.strIndented(depth + 1));
            }
            return s.toString();
        }
    }

    @Override
    public String toString() {
        return strIndented(0);
    }

    // Postcondition: return int[] of length 2:
    // {subtree_total, subtree_size}
    private int[] averageHelper() {
        if (isEmpty()) {
            return new int[2];
        } else {
            int total = root;
            int size = 1;

            for (Tree subtree : subtrees) {
                int[] a = subtree.averageHelper();

                total += a[0];
                size += a[1];
            }

            return new int[]{total, size};
        }
    }

    public float average() {
        if (isEmpty()) {
            return 0.0F;
        } else {
            int[] a = averageHelper();
            return (float) a[0] / a[1];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tree)) return false;
        if (this == o) return true;

        Tree other = (Tree) o;
        if (isEmpty() && other.isEmpty()) {
            return true;
        }
        else if (isEmpty() || other.isEmpty()) {
            return false;
        }
        else {
            if (!root.equals(other.root)) {
                return false;
            }

            if (subtrees.size() != other.subtrees.size()) {
                return false;
            }

            return subtrees.equals(other.subtrees);
        }
    }

    @Override
    public int hashCode() {
        return size() + averageHelper()[0];
    }

    public boolean contains(int item) {
        if (isEmpty()) {
            return false;
        } else {
            if (root.equals(item)) {
                return true;
            }

            for (Tree subtree : subtrees) {
                if (subtree.contains(item)) {
                    return true;
                }
            }
            return false;
        }
    }

    public List<Integer> leaves() {
        if (isEmpty()) {
            return new ArrayList<>();
        } else if (subtrees.isEmpty()) {
            return new ArrayList<>(Collections.singletonList(root));
        } else {
            List<Integer> l = new ArrayList<Integer>();
            for (Tree subtree : subtrees) {
                l.addAll(subtree.leaves());
            }
            return l;
        }
    }

    public boolean deleteItem(int item) {
        if (isEmpty()) {
            return false;
        } else if (root == item) {
            deleteRoot();
            return true;
        } else {
            // loop through each subtree, and stop the first time the item is deleted.
            for (Tree subtree : subtrees) {
                boolean deleted = subtree.deleteItem(item);
                if (deleted && subtree.isEmpty()) {
                    subtrees.remove(subtree);
                    return true;
                } else if (deleted) {
                    // deleted and subtree isn't empty
                    return true;
                }
            }
            return false;
        }
    }

    private void deleteRoot() {
        if (subtrees.isEmpty()) {
            root = null;
        } else {
            Tree chosenSubtree = subtrees.removeFirst();
            root = chosenSubtree.root;
            subtrees.addAll(chosenSubtree.subtrees);
        }
    }

    private int extractLeaf() {
        if (subtrees.isEmpty()) {
            int oldRoot = root;
            root = null;
            return oldRoot;
        } else {
            int leaf = subtrees.getFirst().extractLeaf();
            // need to check if subtrees[0] is now empty
            
            if (subtrees.getFirst().isEmpty()) {
                subtrees.removeFirst();
            }

            return leaf;
        }
    }

    public void insert(int item) {
        if (isEmpty()) {
            root = item;
        } else if (subtrees.isEmpty()) {
            subtrees = new ArrayList<Tree>();
            subtrees.add(
                    new Tree(item, new ArrayList<Tree>())
            );
        } else {
            if (random.nextInt(1,4) == 3) {
                subtrees.add(
                        new Tree(item, new ArrayList<Tree>())
                );
            } else {
                int subtreeIndex = random.nextInt(0, subtrees.size());
                subtrees.get(subtreeIndex).insert(item);
            }
        }
    }

    public boolean insertChild(int item, int parent) {
        if (isEmpty()) {
            return false;
        } else if (root == parent) {
            subtrees.add(
                    new Tree(item, new ArrayList<Tree>())
            );
            return true;
        } else {
            for (Tree subtree : subtrees) {
                if (subtree.insertChild(item, parent)) {
                    return false;
                }
            }
            return false;
        }
    }
}
