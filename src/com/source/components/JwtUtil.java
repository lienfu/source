package com.source.components;



import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.source.model.User;
import com.source.tools.JacksonJsonUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	public static final String JWT_ID = "jwt";
	public static final String JWT_SECRET = "hong1mu2zhi3ruan4jian5";
	public static final int JWT_TTL = 60*60*1000;  //millisecond
	public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
	public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
	
	 private String jianshu;
	 
	 /**
	     * 由字符串生成加密key
	     * @return
	 */
	 public SecretKey generalKey(){
		 String stringKey = jianshu+JWT_SECRET; 
		 byte[] encodedKey = Base64.decodeBase64(stringKey);
		 SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		 return key;
	 }
	 
   /**
     * 创建jwt
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
	 
	 public  String createJWT(String id, String subject, long ttlMillis) throws Exception {
		 SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		 long nowMillis = System.currentTimeMillis();
		 Date now = new Date(nowMillis);
		 SecretKey key = generalKey();
		 JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).signWith(signatureAlgorithm, key);
		 if (ttlMillis >= 0) {
			 long expMillis = nowMillis + ttlMillis;
			 Date exp = new Date(expMillis);
			 builder.setExpiration(exp);
		 }
		 return builder.compact();
	 }
	 
    /**
     * 解密jwt
     * @param jwt
     * @return
     * @throws Exception
     */
	 
	 public Claims parseJWT(String jwt) throws Exception{
		 SecretKey key = generalKey();
		 Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
		 return claims;
	 }
	 
    /**
     * 生成subject信息
     * @param user
     * @return
     * @throws Exception 
     */
	 
	 public static String generalSubject(User user) throws Exception{
		 return JacksonJsonUtil.beanToJson(user);
		 
	 }
}
