/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Response;

public class InsertResult {
    
// You can put this in its own file
    public boolean success;
    public String message;
    public int insertedId = 0; // optional, default 0 if not set

    public InsertResult(boolean success, String message) {
        this(success, message, 0);
    }

    public InsertResult(boolean success, String message, int insertedId) {
        this.success = success;
        this.message = message;
        this.insertedId = insertedId;
    }
}
