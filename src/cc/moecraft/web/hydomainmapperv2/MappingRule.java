package cc.moecraft.web.hydomainmapperv2;

/**
 * 此类由 Hykilpikonna 在 2018/04/19 创建!
 * Created by Hykilpikonna on 2018/04/19!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class MappingRule
{
    public static final MappingRule MAPPING_RULE_NOT_EXIST = new MappingRule("INVALID_FROM", "INVALID_TO", MappingMethod.REDIRECT);

    private String from;
    private String to;
    private MappingMethod method;

    public MappingRule(String from, String to, MappingMethod method)
	{
        this.from = from;
        this.to = to;
        this.method = method;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof MappingRule)
        {
            MappingRule other = (MappingRule) obj;

            return other.from.equals(from) && other.to.equals(to) && other.method.equals(method);
        }
        return false;
    }

    public String getFrom()
	{
        return from;
    }

    public void setFrom(String from)
	{
        this.from = from;
    }

    public String getTo()
	{
        return to;
    }

    public void setTo(String to)
	{
        this.to = to;
    }

    public MappingMethod getMethod()
	{
        return method;
    }

    public void setMethod(MappingMethod method)
	{
        this.method = method;
    }
}
