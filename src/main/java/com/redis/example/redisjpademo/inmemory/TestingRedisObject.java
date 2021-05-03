package com.redis.example.redisjpademo.inmemory;

import com.redis.example.redisjpademo.config.RedisSequence;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;

// 2592000000
// 2592000000
@RedisHash(value = "testingRedisObject", timeToLive = 25l)
//@RedisHash(value = "testingRedisObject")
public class TestingRedisObject {

  @Id
  private Integer idTestingRedisObject = 1000000 + Math.toIntExact(RedisSequence.getSequence().longValue());

  private @Indexed
  Integer idSiswa;

  private @Indexed Integer idProfil;

  private @Indexed Integer idJadwalProfil;

  private String name;

  private Date createDate;

  private Date updatedDate;

  public Integer getIdTestingRedisObject() {
    return idTestingRedisObject;
  }

  public void setIdTestingRedisObject(Integer idTestingRedisObject) {
    this.idTestingRedisObject = idTestingRedisObject;
  }

  public Integer getIdSiswa() {
    return idSiswa;
  }

  public void setIdSiswa(Integer idSiswa) {
    this.idSiswa = idSiswa;
  }

  public Integer getIdProfil() {
    return idProfil;
  }

  public void setIdProfil(Integer idProfil) {
    this.idProfil = idProfil;
  }

  public Integer getIdJadwalProfil() {
    return idJadwalProfil;
  }

  public void setIdJadwalProfil(Integer idJadwalProfil) {
    this.idJadwalProfil = idJadwalProfil;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }
}
