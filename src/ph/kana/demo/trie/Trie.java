package ph.kana.demo.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class Trie {

    private Node root = Node.ROOT;

    public void insert(String string) {
        insert(root, string);
    }

    public String findPrefix(String string) {
        var current = root;
        Node longest = null;

        for (var ch : string.toCharArray()) {
            if (current.valid) {
                longest = current;
            }

            current = current.getChild(ch)
                .orElse(current);
        }
        return nonNull(longest)? longest.value : null;
    }

    @Override
    public String toString() {
        return "trie=" + buildString(root, 0);
    }

    private String buildString(Node node, int level) {
        var builder = new StringBuilder();
        if (Node.ROOT != node) {
            builder.append('\n');
            for (int i = 0; i < level; i++) {
                builder.append("  ");
            }
            builder.append(node.value.charAt(node.value.length()-1));
            builder.append(node.valid? '*': "");
        }
        var children = node.children;
        if (!children.isEmpty()) {
            builder.append(" {");
            var childrenString = children.values()
                .stream()
                .map(n -> buildString(n, level+1))
                .collect(Collectors.joining(","));
            builder.append(childrenString);
            builder.append('\n');
            for (int i = 0; i < level; i++) {
                builder.append("  ");
            }
            builder.append('}');
        }

        return builder.toString();
    }

    private void insert(Node node, String string) {
        if (!string.isEmpty()) {
            var ch = string.charAt(0);

            var child = node.children
                .computeIfAbsent(ch, node::createChild);
            insert(child, string.substring(1));
        } else {
            node.valid = true;
        }
    }

    private static class Node {
        private final String value;
        private final Map<Character, Node> children;
        private boolean valid;

        final static Node ROOT = new Node("");

        Node(String value) {
            this.value = value;
            this.children = new HashMap<>();
        }

        Optional<Node> getChild(char value) {
            return Optional.ofNullable(children.get(value));
        }

        Node createChild(char value) {
            return new Node(this.value + value);
        }

        @Override
        public String toString() {
            return String.format("{value:%s%s, children:%s}",
                value,
                valid ? "*": "",
                children.keySet().toString());
        }
    }
}
