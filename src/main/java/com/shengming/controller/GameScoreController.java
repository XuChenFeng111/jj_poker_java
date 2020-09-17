package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.dao.UserMapper;
import com.shengming.entity.GameEndMsgDTO;
import com.shengming.entity.GamePlayerDTO;
import com.shengming.entity.GameScoreDTO;
import com.shengming.entity.UserDetailsDTO;
import com.shengming.entity.result.Result;
import com.shengming.service.GameScoreService;
import com.shengming.service.RoomService;
import com.shengming.utils.ResultsUtils;
import com.shengming.utils.VerTokenUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 游戏结束界面
 *
 * @author XuChenFeng
 * @Date 2020/9/7 15:48
 */
@RestController
@RequestMapping("/game")
@Transactional
public class GameScoreController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    VerTokenUtils verTokenUtils;

    @Autowired
    private GameScoreService gameScoreService;
    @Autowired
    private RoomService roomService;

    public final static String UPLOAD_PATH_PREFIX = "static/uploadFile/";

//    private static List<GamePlayerDTO> list = new ArrayList<>();

//    @InitBinder
//    protected void init(ServletRequestDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//    }


    @RequestMapping("/getGameScore")
    public Result getGameScore(@RequestBody List<GameEndMsgDTO> gameEndMsgDTOS) {
        try {
            for (GameEndMsgDTO gameEndMsgDTO : gameEndMsgDTOS) {
                String token = gameEndMsgDTO.getToken();
                Integer userid = gameEndMsgDTO.getUserid();
                //当局游戏输赢的金币或金豆值
                //验证token 0：成功 ；1：失败
                String tokenByUserId = userMapper.findTokenByUserId(userid);
                String isok = verTokenUtils.verToken(token, tokenByUserId);
                if (isok.equals("0")) {
                    GameScoreDTO gameScoreDTO = new GameScoreDTO();
                    UserDetailsDTO userDetailsDTO = userMapper.selectByPrimaryKey(userid);
                    //游戏开始前的账户余额
                    Integer pointFromDB = userDetailsDTO.getPoint();
                    Integer goldcoinFromDB = userDetailsDTO.getGoldcoin();
                    //给数据库存值
                    gameScoreDTO.setMemberFkUserId(userid);
                    gameScoreDTO.setGoldenBeansBefore(pointFromDB);
                    gameScoreDTO.setGoldenCoinsBefore(goldcoinFromDB);
                    //游戏开始时间
                    Timestamp timestamp = new Timestamp(gameEndMsgDTO.getBeginTime().getTime());
                    gameScoreDTO.setGamebegintime(timestamp);
                    //游戏结束时间
//                    gameScoreDTO.setGameendtime(gameEndMsgDTO.getEndTime());
                    //游戏输赢 0失败 1胜利
                    gameScoreDTO.setIswin(gameEndMsgDTO.getIswin());
                    //当局游戏输赢的金币或金豆值
                    gameScoreDTO.setGoldenBeansValue(gameEndMsgDTO.getPoint());
                    gameScoreDTO.setGoldenCoinsValue(gameEndMsgDTO.getGoldCoin());
                    //游戏类型
                    gameScoreDTO.setGametype(gameEndMsgDTO.getGametype());
                    if (pointFromDB + gameEndMsgDTO.getPoint() <= 0) {
                        gameScoreDTO.setGoldenBeansAfter(0);
                        userDetailsDTO.setPoint(0);
                    } else {
                        gameScoreDTO.setGoldenBeansAfter(pointFromDB + gameEndMsgDTO.getPoint());
                        userDetailsDTO.setPoint(pointFromDB + gameEndMsgDTO.getPoint());
                    }
                    if (goldcoinFromDB + gameEndMsgDTO.getGoldCoin() <= 0) {
                        gameScoreDTO.setGoldenCoinsAfter(0);
                        userDetailsDTO.setGoldcoin(0);
                    } else {
                        gameScoreDTO.setGoldenCoinsAfter(goldcoinFromDB + gameEndMsgDTO.getGoldCoin());
                        userDetailsDTO.setGoldcoin(goldcoinFromDB + gameEndMsgDTO.getGoldCoin());
                    }
                    gameScoreService.insert(gameScoreDTO);
                    userMapper.updateAmount(userDetailsDTO);
                } else {
                    return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return ResultsUtils.successWithData("", BaseEnums.SUCCESS.code(), "存储成功");
    }


    @RequestMapping("/removeRoom")
    public Result removeRoom(Integer gametype, String userids) {
        roomService.deleteByPlayNum(gametype, userids);
        return ResultsUtils.successWithData("", BaseEnums.SUCCESS.code(), "内部清除房间成功");
    }


    @RequestMapping("/judgeIsOperation")
    public Result judgeIsOperation(@RequestBody GamePlayerDTO gamePlayerDTO) throws IOException {
//        String contextPath = request.getContextPath();
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        int isready = 0;
        Integer userid = gamePlayerDTO.getUserid();
        String userids = gamePlayerDTO.getUserids();
        Integer gametype = gamePlayerDTO.getGametype();
        String[] strings = userids.split(",");

//        File dir_File = new File(dirPath + "/" +userids +".txt");
        File dir_File = new File(realPath + "/" + userids + ".txt");
        String fileName = dir_File.getName();
        System.out.println(dir_File.getPath()); // \JQ_Resource\temp\hr\dir_file.txt
//        System.out.println(file.exists()); // true

        if (Arrays.asList(strings).contains(userid.toString())) {
//            list.add(gamePlayerDTO);
            // Write to file，true为在末尾继续写，false为覆盖原先的内容
            BufferedWriter out = new BufferedWriter(new FileWriter(dir_File, true));
            out.write("操作成功");
            out.newLine();

            out.close();
            long lineNumber = getLineNumber(dir_File);
            System.out.println(lineNumber);
            if (gametype == 1) {
                if (lineNumber == 4) {
//                    list = null;
                    deleteFile(fileName);
                    return ResultsUtils.successWithData(isready, BaseEnums.SUCCESS.code(), "所有操作完成,请发牌");
                } else {
                    isready = 1;
                    return ResultsUtils.failureWithData(isready, BaseEnums.FAILURE.code(), "没有操作结束");
                }
            } else {
                if (lineNumber == 5) {
//                    list = null;
                    deleteFile(fileName);
                    return ResultsUtils.successWithData(isready, BaseEnums.SUCCESS.code(), "所有操作完成，请发牌");
                } else {
                    isready = 1;
                    return ResultsUtils.failureWithData(isready, BaseEnums.FAILURE.code(), "没有操作结束");
                }
            }
        } else {
            return ResultsUtils.failure("未知错误");
        }
    }


    public long getLineNumber(File file) {
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
                lineNumberReader.skip(Long.MAX_VALUE);
                long lines = lineNumberReader.getLineNumber() + 1;
                fileReader.close();
                lineNumberReader.close();
                return lines;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String sPath) {
        Boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

}
