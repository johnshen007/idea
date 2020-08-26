// Java program to print ancestors of given node 
//打印给定节点的所有祖先节点
/* A binary tree node has data, pointer to left child 
   and a pointer to right child */
//class Node
//{
//    int data;
//    Node left, right, nextRight;
//
//    Node(int item)
//    {
//        data = item;
//        left = right = nextRight = null;
//    }
//}

class BinaryTree2
{
    Node root;

    /* If target is present in tree, then prints the ancestors 
       and returns true, otherwise returns false.(用于判断给定节点数据值target是否
       是当前节点node的指或在其左右子树中) */
    boolean printAncestors(Node node, int target)
    { 
         /* base cases */
        if (node == null)
            return false;

        if (node.data == target)
            return true; 
   
        /* If target is present in either left or right subtree  
           of this node, then print this node */
        if (printAncestors(node.left, target)
                || printAncestors(node.right, target))
        {
            System.out.print(node.data + " ");
            return true;
        } 
   
        /* Else return false */
        return false;
    }

    /* Driver program to test above functions */
    public static void main(String args[])
    {
        BinaryTree2 tree = new BinaryTree2();
          
        /* Construct the following binary tree 
                  1 
                /   \ 
               2     3 
              /  \ 
             4    5 
            / 
           7 
        */
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.left.left.left = new Node(7);

        tree.printAncestors(tree.root, 7);

    }
}

// This code has been contributed by Mayank Jaiswal 