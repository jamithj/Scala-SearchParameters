package monash.canonical.searchParameters {

  case class AnySearchParameters(a: SearchParameter*) extends SearchParameter {
    override def filter(ar: Any): Boolean = a.foldLeft(false)((acc, item) => acc || item.filter(ar))
  }
  case class AllSearchParameters(a: SearchParameter*) extends SearchParameter {
    override def filter(ar: Any): Boolean = a.foldLeft(true)((acc, item) => acc && item.filter(ar))
  }
  case class NotSearchParameter(a: SearchParameter) extends SearchParameter {
    override def filter(ar: Any): Boolean = a.filter(ar)
  }

  trait SearchParameter {
    def filter(ar: Any): Boolean
    def &&(u: SearchParameter): SearchParameter = AllSearchParameters(this, u)
    def ||(u: SearchParameter): SearchParameter = AnySearchParameters(this, u)
  }

  case class Max(count: Int) extends SearchParameter {
    protected var currentMatches = 0
    override def filter(ar: Any): Boolean = {
      currentMatches += 1
      currentMatches <= count
    }
  }
  
  case class Skip(count: Int) extends SearchParameter {
    protected var currentlySkipped = 0
    override def filter(ar: Any): Boolean = {
      currentlySkipped += 1
      currentlySkipped > count
    }
  }

}
