package me.strongwhisky.app.day31.common.criteria;

/**
 * Created by taesu on 2018-06-13.
 */
public enum SearchOperation {
    EQUAL("="),
    NOT_EQUAL("!="),
    LIKE(":"),
    GREATER_EQUAL_THAN(">="),
    GREATER_THAN(">"),
    LESS_EQUAL_THAN("<="),
    LESS_THAN("<");

    private String operation;

    SearchOperation(String operation) {
        this.operation = operation;
    }

    public static SearchOperation getOperationFrom(String string){
        if(string.equalsIgnoreCase("=")){
            return EQUAL;
        } else if(string.equalsIgnoreCase("!=")){
            return NOT_EQUAL;
        } else if(string.equalsIgnoreCase(":")){
            return LIKE;
        } else if(string.equalsIgnoreCase("<=")){
            return LESS_EQUAL_THAN;
        } else if(string.equalsIgnoreCase("<")){
            return LESS_THAN;
        } else if(string.equalsIgnoreCase(">=")){
            return GREATER_EQUAL_THAN;
        } else if(string.equalsIgnoreCase(">")){
            return GREATER_THAN;
        }
        return EQUAL;
    }
}
