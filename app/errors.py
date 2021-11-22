class DependencyNotFound(Exception):
    """Raised when the dependency is declared but not found in elements tree"""

    def __init__(
        self,
        message="One or more of the declared dependencies is not presented in base elements!",
    ):
        self.message = message
        super().__init__(self.message)


class NoLabelForButton(Exception):
    """Raised when there is no label defined for button"""

    def __init__(self, message="No label defined for one or more buttons!"):
        self.message = message
        super().__init__(self.message)


class InvalidYamlFormat(Exception):
    """Raised when yaml content is not list"""

    def __init__(self, message="Invalid template format!"):
        self.message = message
        super().__init__(self.message)
