// ORM class for table 'sustancia_activa_europa_con_traza'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Tue Apr 10 15:16:42 CEST 2018
// For connector: org.apache.sqoop.manager.DirectMySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class sustancia_activa_europa_con_traza extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private Long id;
  public Long get_id() {
    return id;
  }
  public void set_id(Long id) {
    this.id = id;
  }
  public sustancia_activa_europa_con_traza with_id(Long id) {
    this.id = id;
    return this;
  }
  private String real_id;
  public String get_real_id() {
    return real_id;
  }
  public void set_real_id(String real_id) {
    this.real_id = real_id;
  }
  public sustancia_activa_europa_con_traza with_real_id(String real_id) {
    this.real_id = real_id;
    return this;
  }
  private String tipo;
  public String get_tipo() {
    return tipo;
  }
  public void set_tipo(String tipo) {
    this.tipo = tipo;
  }
  public sustancia_activa_europa_con_traza with_tipo(String tipo) {
    this.tipo = tipo;
    return this;
  }
  private String language;
  public String get_language() {
    return language;
  }
  public void set_language(String language) {
    this.language = language;
  }
  public sustancia_activa_europa_con_traza with_language(String language) {
    this.language = language;
    return this;
  }
  private String name;
  public String get_name() {
    return name;
  }
  public void set_name(String name) {
    this.name = name;
  }
  public sustancia_activa_europa_con_traza with_name(String name) {
    this.name = name;
    return this;
  }
  private String source;
  public String get_source() {
    return source;
  }
  public void set_source(String source) {
    this.source = source;
  }
  public sustancia_activa_europa_con_traza with_source(String source) {
    this.source = source;
    return this;
  }
  private String jhi_date;
  public String get_jhi_date() {
    return jhi_date;
  }
  public void set_jhi_date(String jhi_date) {
    this.jhi_date = jhi_date;
  }
  public sustancia_activa_europa_con_traza with_jhi_date(String jhi_date) {
    this.jhi_date = jhi_date;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof sustancia_activa_europa_con_traza)) {
      return false;
    }
    sustancia_activa_europa_con_traza that = (sustancia_activa_europa_con_traza) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.real_id == null ? that.real_id == null : this.real_id.equals(that.real_id));
    equal = equal && (this.tipo == null ? that.tipo == null : this.tipo.equals(that.tipo));
    equal = equal && (this.language == null ? that.language == null : this.language.equals(that.language));
    equal = equal && (this.name == null ? that.name == null : this.name.equals(that.name));
    equal = equal && (this.source == null ? that.source == null : this.source.equals(that.source));
    equal = equal && (this.jhi_date == null ? that.jhi_date == null : this.jhi_date.equals(that.jhi_date));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof sustancia_activa_europa_con_traza)) {
      return false;
    }
    sustancia_activa_europa_con_traza that = (sustancia_activa_europa_con_traza) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.real_id == null ? that.real_id == null : this.real_id.equals(that.real_id));
    equal = equal && (this.tipo == null ? that.tipo == null : this.tipo.equals(that.tipo));
    equal = equal && (this.language == null ? that.language == null : this.language.equals(that.language));
    equal = equal && (this.name == null ? that.name == null : this.name.equals(that.name));
    equal = equal && (this.source == null ? that.source == null : this.source.equals(that.source));
    equal = equal && (this.jhi_date == null ? that.jhi_date == null : this.jhi_date.equals(that.jhi_date));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.id = JdbcWritableBridge.readLong(1, __dbResults);
    this.real_id = JdbcWritableBridge.readString(2, __dbResults);
    this.tipo = JdbcWritableBridge.readString(3, __dbResults);
    this.language = JdbcWritableBridge.readString(4, __dbResults);
    this.name = JdbcWritableBridge.readString(5, __dbResults);
    this.source = JdbcWritableBridge.readString(6, __dbResults);
    this.jhi_date = JdbcWritableBridge.readString(7, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.id = JdbcWritableBridge.readLong(1, __dbResults);
    this.real_id = JdbcWritableBridge.readString(2, __dbResults);
    this.tipo = JdbcWritableBridge.readString(3, __dbResults);
    this.language = JdbcWritableBridge.readString(4, __dbResults);
    this.name = JdbcWritableBridge.readString(5, __dbResults);
    this.source = JdbcWritableBridge.readString(6, __dbResults);
    this.jhi_date = JdbcWritableBridge.readString(7, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(real_id, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(tipo, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(language, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(name, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(source, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(jhi_date, 7 + __off, 12, __dbStmt);
    return 7;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(real_id, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(tipo, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(language, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(name, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(source, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(jhi_date, 7 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.id = null;
    } else {
    this.id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.real_id = null;
    } else {
    this.real_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.tipo = null;
    } else {
    this.tipo = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.language = null;
    } else {
    this.language = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.name = null;
    } else {
    this.name = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.source = null;
    } else {
    this.source = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.jhi_date = null;
    } else {
    this.jhi_date = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.id);
    }
    if (null == this.real_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, real_id);
    }
    if (null == this.tipo) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, tipo);
    }
    if (null == this.language) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, language);
    }
    if (null == this.name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, name);
    }
    if (null == this.source) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, source);
    }
    if (null == this.jhi_date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, jhi_date);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.id);
    }
    if (null == this.real_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, real_id);
    }
    if (null == this.tipo) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, tipo);
    }
    if (null == this.language) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, language);
    }
    if (null == this.name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, name);
    }
    if (null == this.source) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, source);
    }
    if (null == this.jhi_date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, jhi_date);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 59, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(real_id==null?"null":real_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tipo==null?"null":tipo, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(language==null?"null":language, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(name==null?"null":name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(source==null?"null":source, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(jhi_date==null?"null":jhi_date, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(real_id==null?"null":real_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tipo==null?"null":tipo, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(language==null?"null":language, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(name==null?"null":name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(source==null?"null":source, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(jhi_date==null?"null":jhi_date, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 59, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.real_id = null; } else {
      this.real_id = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.tipo = null; } else {
      this.tipo = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.language = null; } else {
      this.language = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.name = null; } else {
      this.name = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.source = null; } else {
      this.source = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.jhi_date = null; } else {
      this.jhi_date = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.real_id = null; } else {
      this.real_id = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.tipo = null; } else {
      this.tipo = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.language = null; } else {
      this.language = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.name = null; } else {
      this.name = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.source = null; } else {
      this.source = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.jhi_date = null; } else {
      this.jhi_date = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    sustancia_activa_europa_con_traza o = (sustancia_activa_europa_con_traza) super.clone();
    return o;
  }

  public void clone0(sustancia_activa_europa_con_traza o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("real_id", this.real_id);
    __sqoop$field_map.put("tipo", this.tipo);
    __sqoop$field_map.put("language", this.language);
    __sqoop$field_map.put("name", this.name);
    __sqoop$field_map.put("source", this.source);
    __sqoop$field_map.put("jhi_date", this.jhi_date);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("real_id", this.real_id);
    __sqoop$field_map.put("tipo", this.tipo);
    __sqoop$field_map.put("language", this.language);
    __sqoop$field_map.put("name", this.name);
    __sqoop$field_map.put("source", this.source);
    __sqoop$field_map.put("jhi_date", this.jhi_date);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("id".equals(__fieldName)) {
      this.id = (Long) __fieldVal;
    }
    else    if ("real_id".equals(__fieldName)) {
      this.real_id = (String) __fieldVal;
    }
    else    if ("tipo".equals(__fieldName)) {
      this.tipo = (String) __fieldVal;
    }
    else    if ("language".equals(__fieldName)) {
      this.language = (String) __fieldVal;
    }
    else    if ("name".equals(__fieldName)) {
      this.name = (String) __fieldVal;
    }
    else    if ("source".equals(__fieldName)) {
      this.source = (String) __fieldVal;
    }
    else    if ("jhi_date".equals(__fieldName)) {
      this.jhi_date = (String) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
  public boolean setField0(String __fieldName, Object __fieldVal) {
    if ("id".equals(__fieldName)) {
      this.id = (Long) __fieldVal;
      return true;
    }
    else    if ("real_id".equals(__fieldName)) {
      this.real_id = (String) __fieldVal;
      return true;
    }
    else    if ("tipo".equals(__fieldName)) {
      this.tipo = (String) __fieldVal;
      return true;
    }
    else    if ("language".equals(__fieldName)) {
      this.language = (String) __fieldVal;
      return true;
    }
    else    if ("name".equals(__fieldName)) {
      this.name = (String) __fieldVal;
      return true;
    }
    else    if ("source".equals(__fieldName)) {
      this.source = (String) __fieldVal;
      return true;
    }
    else    if ("jhi_date".equals(__fieldName)) {
      this.jhi_date = (String) __fieldVal;
      return true;
    }
    else {
      return false;    }
  }
}
