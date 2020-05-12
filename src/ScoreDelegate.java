
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oripa
 */
public interface ScoreDelegate {

    public void incrementInterface(boolean special);

    public void reset();

    public void checkRecord() throws IOException;
}
