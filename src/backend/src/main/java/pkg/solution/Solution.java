package pkg.solution;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class Solution {
    private long time;
    private List<String> path;
    private int total_nodes;

    // Error handling
    // code 0: no error
    // code 1: start or goal not in dictionary or is a stop word
    // code 2: length of the start string != length of the goal string
    // code 3: no solution found
    private int error_code;
    private String error_message;
    private long memory;

    public Solution(long time, List<String> path, int total_nodes, long memory) {
        this.time = time;
        this.path = path;
        this.total_nodes = total_nodes;
        this.error_code = 0;
        this.error_message = "";
        this.memory = memory;
    }

    public Solution(int error_code) {
        this.error_code = error_code;
        if (error_code == 1) {
            this.error_message = "start or goal not in dictionary or is a stop word";
        } else if (error_code == 2) {
            this.error_message = "length of the start string != length of the goal string";
        } else if (error_code == 3) {
            this.error_message = "no solution found";
        }
    }

    public long getTime() {
        return time;
    }

    public List<String> getPath() {
        return path;
    }

    public int getTotalNodes() {
        return total_nodes;
    }

    public JsonNode toJson() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("time", time);
        node.put("total_nodes", total_nodes);
        node.put("error_code", error_code);
        node.put("error_message", error_message);
        node.put("memory", memory);

        if (error_code != 0) {
            return node;
        }

        ArrayNode pathNode = mapper.createArrayNode();
        for (String word : path) {
            pathNode.add(word);
        }
        node.set("path", pathNode);
        
        return node;
    }
}
