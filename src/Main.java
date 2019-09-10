import java.util.Scanner;

public class Main {
    /**
     * @문제 이진 트리를 입력받아 전위 순회(preorder traversal),
     * 중위 순회(inorder traversal),
     * 후위 순회(postorder traversal)한 결과를 출력하는 프로그램을 작성하시오.
     * 예를 들어 위와 같은 이진 트리가 입력되면,
     * 전위 순회한 결과 : ABDCEFG // (루트) (왼쪽 자식) (오른쪽 자식)
     * 중위 순회한 결과 : DBAECFG // (왼쪽 자식) (루트) (오른쪽 자식)
     * 후위 순회한 결과 : DBEGFCA // (왼쪽 자식) (오른쪽 자식) (루트)
     * 가 된다.
     * @입력 첫째 줄에는 이진 트리의 노드의 개수 N(1≤N≤26)이 주어진다.
     * 둘째 줄부터 N개의 줄에 걸쳐 각 노드와 그의 왼쪽 자식 노드, 오른쪽 자식 노드가 주어진다.
     * 노드의 이름은 A부터 차례대로 영문자 대문자로 매겨지며,
     * 항상 A가 루트 노드가 된다. 자식 노드가 없는 경우에는 .으로 표현된다.
     * @출력 첫째 줄에 전위 순회, 둘째 줄에 중위 순회, 셋째 줄에 후위 순회한 결과를 출력한다.
     * 각 줄에 N개의 알파벳을 공백 없이 출력하면 된다.
     * @예제입력
     * 7
     * A B C
     * B D .
     * C E F
     * E . .
     * F . G
     * D . .
     * G . .
     * @예제출력
     * ABDCEFG
     * DBAECFG
     * DBEGFCA
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        Tree tree = new Tree();

        for (int i = 0; i < num; i++) {
            String input = sc.nextLine();
            String[] inputs = input.split(" ");

            Node leftNode = null;
            if (!".".equals(inputs[1]))
                leftNode = new Node(inputs[1]);

            Node rightNode = null;
            if (!".".equals(inputs[2]))
                rightNode = new Node(inputs[2]);

            tree.add(new Node(inputs[0], leftNode, rightNode));
        }

        BinaryTreeSearcher bts = new BinaryTreeSearcher();
        bts.preOrder(tree.root);
        System.out.println();
        bts.inOrder(tree.root);
        System.out.println();
        bts.postOrder(tree.root);
    }
}

class Node {
    String data;
    Node leftChild;
    Node rightChild;

    public Node(String data) {
        this.data = data;
    }

    public Node(String data, Node leftChild, Node rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}

class Tree {
    Node root;

    void add(Node node) {
        if (root == null)
            root = node;
        else
            search(root, node);
    }

    private void search(Node curNode, Node node) {
        if (curNode == null) return;
        if (curNode.data.equals(node.data)) {
            curNode.leftChild = node.leftChild;
            curNode.rightChild = node.rightChild;
        } else {
            search(curNode.leftChild, node);
            search(curNode.rightChild, node);
        }
    }
}

class BinaryTreeSearcher {
    void preOrder(Node node) {
        if (node == null) return;
        System.out.printf("%s", node.data);
        preOrder(node.leftChild);
        preOrder(node.rightChild);
    }

    void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.leftChild);
        System.out.printf("%s", node.data);
        inOrder(node.rightChild);
    }

    void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        System.out.printf("%s", node.data);
    }
}