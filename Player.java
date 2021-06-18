public class Player {
    public Main game;
    public int[][] pos = new int[20 * 20][2];
    public int size = 1;
    public int[] dir = new int[]{0, 0};
    
    public Player(Main _game){
        game = _game;
        size = 1;
        pos[0][0] = 400;
        pos[0][1] = 400;
        dir[0] = 0;
        dir[1] = 0;
    }

    public void direction(int _x, int _y){
        _x *= game.cellsize;
        _y *= game.cellsize;

        if(size > 2){
            if(pos[0][0] + _x != pos[1][0] || pos[0][1] + _y != pos[1][1]){
                dir[0] = _x;
                dir[1] = _y;
            }
        }else{
            dir[0] = _x;
            dir[1] = _y;
        }
    }

    public void move() {
        checkCollision(pos[0][0] + dir[0], pos[0][1] + dir[1]);

        for(int i = size; i >= 1; i--){
            if(pos[i][0] != pos[i - 1][0])
                pos[i][0] = pos[i - 1][0];
            if(pos[i][1] != pos[i - 1][1])
                pos[i][1] = pos[i - 1][1];
        }

        pos[0][0] += dir[0];
        pos[0][1] += dir[1];

        if(pos[0][0] < 0)
            pos[0][0] = 800 - game.cellsize;
        else if(pos[0][0] >= 800)
            pos[0][0] = 0;
        
        if(pos[0][1] < 0)
            pos[0][1] = 800 - game.cellsize;
        else if(pos[0][1] >= 800)
            pos[0][1] = 0;
    }

    public void checkCollision(int _x, int _y){
        if(_x < 0)
            _x = 800 - game.cellsize;
        else if(_x >= 800)
            _x = 0;
        
        if(_y < 0)
            _y = 800 - game.cellsize;
        else if(_y >= 800)
            _y = 0;

        for(int i = 2; i < size; i++){
            if(pos[i][0] == _x && pos[i][1] == _y){
                reset();
                return;
            }
        }

        if(game.apple[0] == _x && game.apple[1] == _y)
            eatApple();
    }

    public void eatApple(){
        size++;
        game.spawnApple();
    }

    public void reset(){
        size = 1;
        pos[0][0] = 400;
        pos[0][1] = 400;
        dir[0] = 0;
        dir[1] = 0;
        game.spawnApple();
    }
}