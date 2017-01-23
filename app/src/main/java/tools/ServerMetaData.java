package tools;

/**
 * Created by Hoda on 10/01/2017.
 */
public class ServerMetaData {
    public long dateTime;
    public VersionControl VersionControl;

    public static class VersionControl {
        public long CurrentAndroidVersion;
        public long LeastSupportedAndroidVersion;
        public Update Update;
    }

    public static class Update {
        public String Android;
    }
}
